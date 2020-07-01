import React from 'react';
import { BrowserRouter, Switch, Route, NavLink } from 'react-router-dom';
 
import './App.css';
import Login from './component/Login';
import Register from './component/Register';
import Dashboard from './component/Dashboard';
function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <div>
          <div className="content">
            <Switch>
              <Route exact path="/" component={Login} />
              <Route exact path="/register" component={Register} />
              <Route exact path="/dashboard" component={Dashboard} />
            </Switch>
          </div>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
