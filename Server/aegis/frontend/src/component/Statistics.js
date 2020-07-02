import { getUser } from '../utils/Common';
import React, {Component} from 'react';
import axios from 'axios';


function Statistics(props){
    
    const state = {
        users: []
    }
    const goBack = () => {
        props.history.goBack();
    } 

    return(
        <div>
            <h1>Statistics</h1>
            <input type="button" onClick={goBack} value="Back"/>
        </div>
    );
}


export default Statistics;