import React, { useState } from 'react';
import { setUserSession } from '../utils/Common';
import { validateCredentials } from '../utils/Validator';
import CryptoJS from 'crypto-js';
import { encrypt } from '../utils/Cryptography';
import axios from 'axios';
import { Button, Container, Card, Form } from 'react-bootstrap';
function Login(props) {
  const PASSWORD = "This is a password";
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
      var iv = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);
      var salt = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);
      var ciphertext = encrypt(128, 1000, salt, iv, PASSWORD, pw);
      var aesPassword = (iv + "::" + salt + "::" + ciphertext);
      pw = btoa(aesPassword);
      var data = { username: un, password: pw }
      axios.post('http://localhost:8080/api/login', data).then(response => {
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
    } else setError("Illegal characters or empty input fields.")


  }

  const Signup = () => {
    props.history.push("/register")
  }

  return (
    <div>
      <Container>
        <Card>
          <Card.Header>Login</Card.Header>
          <Card.Body>
            <Form>
              <fieldset>
                <div className="row">
                  <div className="col-12 col-lg-6">
                    <label for="username">Username</label>
                    <input className="form-control" placeholder="Username" type="text" {...username} name="username" id="username" autoComplete="new-password" />
                  </div>
                </div>
                <div className="row mt-3">
                  <div className="col-12 col-lg-6">
                    <label for="password">Password</label>
                    <input className="form-control" placeholder="password" type="password" {...password} name="password" id="password" autoComplete="new-password" />
                  </div>
                </div>
                <div className="row mt-3">
                  {error && <><small style={{ color: 'red' }}>{error}</small><br /></>}<br />
                  <Button variant="primary" onClick={handleLogin} disabled={loading}>{loading ? 'Loading...' : 'Login'}</Button>
                </div>
              </fieldset>
            </Form>
          </Card.Body>
        </Card>
      </Container>
      <Button variant="link" onClick={Signup}>Need an Account?</Button>
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