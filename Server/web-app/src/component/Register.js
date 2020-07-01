import React, { useState } from 'react';
import axios from 'axios';
import {validatePassword,validateCredentials} from '../utils/Validator';

function Register(props){
    const username              = useFormInput('');
    const password              = useFormInput('');
    const confPassword          = useFormInput('');
    const [error, setError]     = useState(null);
    const [loading, setLoading] = useState(false);

    // handle button lick of register form
    const handleRegister = () => {
        setError(null);
        if(validateCredentials(username.value.trim(),password.value.trim()) && validatePassword(password.value.trim(), confPassword.value.trim())){
            setLoading(true);
            axios.post('http://localhost:8080/api/register', {username: username.value.trim(), password: password.value.trim()}).then(response =>{
                setLoading(false);
                props.history.push('/');
            }).catch(error =>{
                  setLoading(false);
                  console.log(error);
                    setError("Can not use that username.");
            });
        }else{
            setLoading(false);
            setError("Passwords are empty or do not match.");
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