import { getUser } from '../utils/Common';
import React, { Component } from 'react';
import axios from 'axios';
import Chart from 'react-apexcharts';

class Statistics extends Component {
    chartRef    = React.createRef();
    //socket      = new WebSocket("ws://localhost:4444");; 
    constructor(props) {
        super(props); 
        this.state = { 
            intersections: []
        };
    }

    updateState() {
        var self = this;
        //var tl_objects = [];
        var int_objects = [];
        axios.get('http://localhost:8080/simu/getIntersections')
            .then(response => {
                var int_data= response.data; 
                int_data.forEach((intersection, index) => { 
                    var dataStationary = [];
                    var dataMoving = [];
                    var timestamps = [];
                    var i_name = intersection.name;
                    intersection.statistics.forEach((statistic, index) => {
                        const totalStationary = statistic.stationaryX + statistic.stationaryY;
                        const totalMoving = statistic.movingX + statistic.movingY;
                        const timestamp = Date.parse(statistic.timestamp);

                        dataStationary.push(totalStationary);
                        dataMoving.push(totalMoving);
                        timestamps.push(timestamp)

                    });

                    var int_object = {
                        name: i_name,
                        series: [{
                            name: "Stationary Vehicles",
                            data: dataStationary
                        },
                        {
                            name: "Moving Vehicles",
                            data: dataMoving
                        }
                        ],
                        options: {
                            xaxis: {
                                categories: timestamps
                            },
                            yaxis: [
                                {
                                    title: {
                                        text: "Statioanry Vehicles"
                                    }
                                },
                                {
                                    opposite: true,
                                    title: {
                                        text: "Moving Vehicles"
                                    }
                                }
                            ]
                        }

                    };
                    int_objects.push(int_object);
                })
                const intersections = int_objects;
                self.setState({ intersections });
            }).catch(error => {
                console.log(error);
            });
    }
    
    componentDidMount() {
        if (getUser() !== null) {
            this.updateState();
            /*
            this.socket.onopen = function() {
                console.log("Connected!");
                //this.send("Ping");
            };
            this.socket.onmessage = function(msg){
                console.log(msg);
            };

            this.socket.onclose = function() {
                console.log("closed");
            };
            this.socket.onerror = function(e){
                console.log(e);
            }
            */
        }
    }

    goBack = () => {
        this.props.history.push("/dashboard");
    }

    render() {
        return (
            <div>
                <h1>Statistics:</h1>
                <input type="button" onClick={this.goBack} value="Back" /> 
                {this.state.intersections.map(function (trafficlight, index) {
                    return (
                        <div className="mixed-chart">
                            <h1>{trafficlight.name}</h1>
                            <Chart
                                options={trafficlight.options}
                                series={trafficlight.series}
                                width="100%"
                                height="300"
                            />
                        </div>
                    )
                })}
            </div>
        )
    }

}


export default Statistics;