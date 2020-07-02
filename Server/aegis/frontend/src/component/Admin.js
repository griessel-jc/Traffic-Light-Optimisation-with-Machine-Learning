import { getUser } from '../utils/Common';
import React, { Component } from 'react';
import axios from 'axios';

class Admin extends Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            users: [],
            response: {}
        }
    }

    componentDidMount() {
        if(getUser() !== null){
            axios.get('http://localhost:8080/api/getUsers')
            .then(response => {
                //console.log(response.data);
                this.setState({
                    users: response.data
                })
            })
            .catch(error => {
                console.log(error);
            });
        }
    }

    deleteUser(id) {
        const { users } = this.state;
        var r = window.confirm("Are you sure?");
        if(r === true){
            axios.delete('http://localhost:8080/api/deleteUser/' + id)
            .then(result => {
                //console.log(result.data);
                this.setState({
                    response: result,
                    users: users.filter(user => user.id !== id)
                });
            })
            .catch(error => {
                this.setState({ error });
            });
        }
    }

    opUser(user_id, rid){
        const { users } = this.state;
        var r = window.confirm("Are you sure?");
        if(r === true){
            //console.log(getUser());
            rid = (rid ==1 ? 2 : 1);
            var admin = {username : getUser().username, password: getUser().password,id: user_id, role_Id: rid  }
            //console.log(admin);
            axios.post('http://localhost:8080/api/changeRole',admin)
            .then(result => {
                //console.log(result.data);
                if(getUser() !== null){
                    axios.get('http://localhost:8080/api/getUsers')
                    .then(response => {
                        //console.log(response.data);
                        this.setState({
                            users: response.data
                        })
                    })
                    .catch(error => {
                        console.log(error);
                    });
                }
            })
            .catch(error => {
                this.setState({ error });
            });
        }
    }

    render() {
        const { error, users } = this.state;

        if (error) {
            return (
                <div>Error: {error.message}</div>
            )
        } else {
            return (
                <div>
                    <h2>User List</h2>
                    <table>
                        <thead>
                            <tr>
                                <th>Username</th>
                                <th>Role</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {users.map((user) => (
                                <tr key={user.id}>
                                    <td>{user.username}</td>
                                    <td>{user.role.name}</td>
                                    <td>
                                        <button onClick={() => this.opUser(user.id, user.role_Id)}>{user.role.name == "admin"? "De-op" :"Op"}</button>
                                    &nbsp;<button variant="danger" onClick={() => this.deleteUser(user.id)}>Delete</button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )
        }

    }

}

export default Admin;

/*function Admin(props){

    const goBack = () => {
        props.history.goBack();
    }
    return(
        <div>
            <h1>Admin</h1>
            <input onClick={() => props.history.back()}>Back</input>
        </div>
    );
}


export default Admin;*/