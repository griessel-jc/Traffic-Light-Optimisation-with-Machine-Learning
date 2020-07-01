import React, { useState } from 'react';
import axios from 'axios';
import Login from './Login';
import { Router,Route, NavLink, Link } from 'react-router-dom';

function Register(props){
    const username              = useFormInput('');
    const password              = useFormInput('');
    const confPassword          = useFormInput('');
    const [error, setError]     = useState(null);
    const [loading, setLoading] = useState(false);

    // handle button lick of register form
    const handleRegister = () => {
        setError(null);
        setLoading(true);
        console.log(username,password,confPassword);
        if(new String(password).trim().valueOf() === new String(confPassword).trim().valueOf()){
            axios.post('http://localhost:8080/api/register', {username: username.value, password: password.value}).then(response =>{
                setLoading(false);
                const data = response.data;
                props.history.push('/');
            }).catch(error =>{
                  setLoading(false);
                  console.log(error);
                  //if(error.response.status === 401) setError(error.response.data.message);
                  //else setError("Something went wrong. Please try again later.")
            });
        }else{
            setLoading(false);
            setError("Passwords do not match.");
        }
    }

    const Login = () =>{
        props.history.push("/")
      }

    return (
        <div>
            Register<br /><br />
            <div>
                Username<br />
                <input type="text" {...username} autoComplete="new-password" />
            </div>
            <div style={{marginTop: 10}}>
                Password<br />
                <input type="password" {...password} autoComplete="new-password" />
            </div>
            <div style={{marginTop: 10}}>
                Password<br />
                <input type="password" {...confPassword} autoComplete="new-password"/>
            </div>
            {error && <><small style={{ color: 'red' }}>{error}</small><br /></>}<br />
            <input type="button" value={loading ? 'Loading...' : 'Register'} onClick={handleRegister} disabled={loading} /><br />
            <br />
      <input type="button" onClick={Login} value="Already have an account?"/><br />
        
        </div>
    );
}

const useFormInput = initialValue => {
    const [value, setValue] = useState(initialValue);
   
    const handleChange = e => {
      setValue(e.target.value);
    }
    return {
      value,
      onChange: handleChange
    }
  }
   
  export default Register;