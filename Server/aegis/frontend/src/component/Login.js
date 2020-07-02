import React, { useState } from 'react';
import { setUserSession } from '../utils/Common';
import { validateCredentials } from '../utils/Validator';
import CryptoJS from 'crypto-js';
import {encrypt} from '../utils/Cryptography';
import axios from 'axios';
function Login(props) {
  const PASSWORD              = "This is a password";
  const username = useFormInput('');
  const password = useFormInput('');
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);


  // handle button click of login form
  const handleLogin = () => {
    setError(null);
    
    if (validateCredentials(username.value.trim(), password.value.trim())) {
      setLoading(true);
      var un = username.value.trim().toString();
            var pw = password.value.trim().toString();
            var iv      = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
            var salt    = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
            var ciphertext = encrypt(128,1000,salt,iv,PASSWORD,pw);
            var aesPassword = (iv+"::"+salt+"::"+ciphertext);
            console.log("AES:",aesPassword);
            pw    = btoa(aesPassword);
            
            
            var data = {username: un, password: pw}
            console.log(data);
      axios.post('http://localhost:8080/api/login',data).then(response => {
        setLoading(false);
        const data = response.data;
        setUserSession(''/*token*/, data);
        props.history.push('/dashboard');
      }).catch(error => {
        setLoading(false);
        console.log(error);
        setError("Invalid username or password.")
        //if(error.response.status === 401) setError(error.response.data.message);
        //else setError("Something went wrong. Please try again later.")
      });
    }else setError("Illegal characters or empty input fields.")


  }

  const Signup = () => {
    props.history.push("/register")
  }

  return (
    <div>
      Login<br /><br />
      <div>
        Username<br />
        <input type="text" {...username} autoComplete="new-password" />
      </div>
      <div style={{ marginTop: 10 }}>
        Password<br />
        <input type="password" {...password} autoComplete="new-password" />
      </div>
      {error && <><small style={{ color: 'red' }}>{error}</small><br /></>}<br />
      <input type="button" value={loading ? 'Loading...' : 'Login'} onClick={handleLogin} disabled={loading} /><br />
      <br />
      <input type="button" onClick={Signup} value="Need an account?" /><br />

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

export default Login;