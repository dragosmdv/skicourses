import React from "react";
import ReactDOM from "react-dom";
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import { Counter } from 'counter/Counter';
import "./index.css";

const App = () => (
    <div className="container">
        <h1>SkiApp</h1>
        <h4>Learn to ski anywhere</h4>
        <img src="data:image/jpeg;base64,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" alt="W3Schools.com">
        </img>
        <br/>
        <a  href='/about'>About the app</a>
        <Router>
            <Routes>
                <Route path="/about" element={<Counter/>}/>
            </Routes>
        </Router>
    </div>
);
ReactDOM.render(<App />, document.getElementById("app"));
