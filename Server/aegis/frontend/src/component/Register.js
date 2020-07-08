import React, { useState } from 'react';
import axios from 'axios';
import CryptoJS from 'crypto-js';
import { validatePassword, validateCredentials } from '../utils/Validator';
import { encrypt } from '../utils/Cryptography';
import { Container, Card, Button, Form } from 'react-bootstrap';
function Register(props) {
    const PASSWORD = "This is a password";
    const username = useFormInput('');
    const password = useFormInput('');
    const confPassword = useFormInput('');
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);

    // handle button lick of register form
    const handleRegister = () => {
        setError(null);
        if (validateCredentials(username.value.trim(), password.value.trim()) && validatePassword(password.value.trim(), confPassword.value.trim())) {
            setLoading(true);
            var un = username.value.trim().toString();
            var pw = password.value.trim().toString();
            var iv = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);
            var salt = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);

            var ciphertext = encrypt(128, 1000, salt, iv, PASSWORD, pw);
            var aesPassword = (iv + "::" + salt + "::" + ciphertext);
            console.log("AES:", aesPassword);
            pw = btoa(aesPassword);


            var data = { username: un, password: pw }
            console.log(data);
            axios.post('http://localhost:8080/api/registerEnc', data).then(response => {
                setLoading(false);
                props.history.push('/');
            }).catch(error => {
                setLoading(false);
                setError("Can not use that username.");
            });
        } else {
            setLoading(false);
            setError("Passwords are empty or do not match.");
        }
    }

    const Login = () => {
        props.history.push("/")
    }

    return (
        <div>
            <Container>
                <Card>
                    <Card.Header>Register</Card.Header>
                    <Card.Body>
                        <Form >
                            <fieldset>
                                <div className="row">
                                    <div className="col-12 col-lg-6">
                                        <label for="username">Username:</label>
                                        <input className="form-control" type="text" placeholder="Username" {...username} name="username" id="username" />
                                    </div>
                                </div>
                                <div className="row mt-3">
                                    <div className="col-12 col-lg-6">
                                        <label for="password">Create Password:</label>
                                        <input className="form-control" type="password" placeholder="password" {...password} name="password" id="password" />
                                    </div>
                                    <div className="col-12 col-lg-6">
                                        <label for="regEmail">Confirm Password:</label>
                                        <input className="form-control" type="password" placeholder="password" {...confPassword} name="confPassword" id="confPassword" />
                                    </div>
                                </div>
                                <div className="row mt-3">
                                    {error && <><small style={{ color: 'red' }}>{error}</small><br /></>}<br />
                                    <Button variant="primary" onClick={handleRegister} disabled={loading}>{loading ? 'Loading...' : 'Register'}</Button>
                                </div>
                            </fieldset>
                        </Form>
                    </Card.Body>
                </Card>
            </Container>
            <Button variant="link" onClick={Login}>Already have an account?</Button>
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