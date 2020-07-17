import { getUser } from '../utils/Common';
import React, { Component } from 'react';
import axios from 'axios';
import Chart from 'react-apexcharts';

class Statistics extends Component {
    chartRef = React.createRef();
    constructor(props) {
        super(props);
        // this.state = {
        //     error: null,
        //     trafficlights: [],
        //     response: {}
        // }
        this.state = {
            trafficlights: [],
            /*options: {
                chart: {
                    id: "basic-bar"
                },
                xaxis: {
                    categories: [1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998]
                }
            },
            series: [
                {
                    name: "series-1",
                    data: [30, 40, 45, 50, 49, 60, 70, 91]
                }
            ]*/
        };
    }

    updateState() {
        var self = this;
        var tl_objects = [];
        axios.get('http://localhost:8080/api/getTrafficlights')
            .then(response => {
                //console.log(response.data);
                const tl_data = response.data;
                tl_data.forEach((trafficlight, index) => {
                    //console.log(trafficlight);
                    var dataStationary = [];
                    var dataMoving = [];
                    var timestamps = [];
                    var tl_name = trafficlight.name;
                    trafficlight.statistics.forEach((statistic, index) => {
                        const totalStationary = statistic.stationaryX + statistic.stationaryY;
                        const totalMoving = statistic.movingX + statistic.movingY;
                        const timestamp = Date.parse(statistic.timestamp);

                        dataStationary.push(totalStationary);
                        dataMoving.push(totalMoving);
                        timestamps.push(timestamp)

                    });

                    var tl_object = {
                        name: tl_name,
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
                    tl_objects.push(tl_object);
                })
                const trafficlights = tl_objects;
                self.setState({ trafficlights });
            }).catch(error => {
                console.log(error);
            });
    }

    componentDidMount() {
        if (getUser() !== null) {
            this.updateState();

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
                {/* <div className="row">
                    
                    <div className="mixed-chart">
                        <Chart
                            options={this.state.options}
                            series={this.state.series}
                            type="line"
                            width="500"
                        />
                    </div>
                </div> */}
                {this.state.trafficlights.map(function (trafficlight, index) {
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