import { getUser } from '../utils/Common';
import React, { Component } from 'react';
import axios from 'axios';
import Chart from 'react-apexcharts';
import '../css/statistics.css';
import { ReactComponent as BackIcon } from '../images/chevron-left-solid.svg';
import CodehesionLogo from '../images/codehesion_logo.png';
import AegisLogo from '../images/Aegis_logo.png';

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
                            legend: {
                                labels: {
                                    colors: '#fff',
                                    useSeriesColors: false
                                },
                            },
                            xaxis: {
                                categories: timestamps,
                                labels: {
                                    style: {
                                        colors: '#fff',
                                    },
                                },
                            },
                            yaxis: [
                                {
                                    title: {
                                        text: "Statioanry Vehicles",
                                        style: {
                                            color: '#d1d1d1'
                                        },
                                    },
                                    labels: {
                                        style: {
                                            colors: '#fff',
                                        },
                                    },
                                },
                                {
                                    opposite: true,
                                    title: {
                                        text: "Moving Vehicles",
                                        style: {
                                            color: '#d1d1d1'
                                        },
                                    },
                                    labels: {
                                        style: {
                                            colors: '#fff',
                                        },
                                    },
                                },
                            ],
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
                <div className="topbar-wrapper">
                    <div className="topbar-back">
                        <div onClick={this.goBack}>
                            <BackIcon/>
                            <button variant="danger" onClick={this.goBack}>BACK</button>
                        </div>
                    </div>
                    <div className="topbar-title">
                        <div>TRAFFIC LIGHT OPTIMISATION WITH MACHINE LEARNING</div>
                    </div>
                </div>
                
                <div className="stat-wrapper">
                    <h2>Statistics</h2>

                    <div className="scene-wrapper">

                            {this.state.intersections.map(function (trafficlight, index) {
                                return (
                                    <div className="mixed-chart chart-wrapper">
                                        <h4>{trafficlight.name}</h4>
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
                </div>

                    <div className="footer">
                        <div className="footer-content">
                            <p>DEVELOPED BY</p>
                            <img src={AegisLogo}/>
                        </div>

                        <div className="footer-content">
                            <p>PROJECT BY</p>
                            <img src={CodehesionLogo}/>
                        </div>
                        <div className="footer-content">
                            <p>GITHUB</p>
                            <a href="https://github.com/COS301-SE-2020/Traffic-Light-Optimisation-with-Machine-Learning">https://github.com/COS301-SE-2020/Traffic-Light-Optimisation-with-Machine-Learning</a>
                        </div>
                    </div>
            </div>
        )
    }

}


export default Statistics;