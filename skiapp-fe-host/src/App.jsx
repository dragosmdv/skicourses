import React from "react";
import ReactDOM from "react-dom";
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import { About } from 'about/About';
import "./index.css";
import { Button } from '@material-ui/core';
import CourseList from "./pages/courses/CourseList";
import Home from "./Home";



const App =  ({ navigation }) => {
return (
    <div className="App">
        <Router>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/courses" element={<CourseList/>}/>
                <Route path="/about" element={<About/>}/>
            </Routes>
        </Router>
    </div>
);
}
ReactDOM.render(<App />, document.getElementById("app"));

