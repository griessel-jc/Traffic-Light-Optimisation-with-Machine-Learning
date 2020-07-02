import React from 'react';
import { getUser, removeUserSession } from '../utils/Common'; 
function Dashboard(props) {
    const user = getUser();

    //handle click event of logout button
    const handleLogout = () => {
        removeUserSession();
        props.history.push('/');
    }

    const goBack = () => {
        props.history.goBack();
    }

    const handleAdmin = () => {
        props.history.push("/admin");
    }

    const handleStatistics = () => {
        props.history.push("/statistics");
    } 
    
    return (
        <div>
            Welcome {user.username}!<br /><br />
            <input type="button" onClick={handleLogout} value="Logout" />
            <div className="Menu">
                {user.role.id === 1 && <input type="button" onClick={handleAdmin} value="Admin"></input>}
                <input type="button" onClick={handleStatistics} value="Statistics"/>
            </div>
        </div>
       
    );
}

export default Dashboard;