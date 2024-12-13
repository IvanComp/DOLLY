import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import AOS from "aos";
import "aos/dist/aos.css";
import "./home.css";
import developer from "Frontend/img/develop.gif";
import monitoring from "Frontend/img/monitoring.gif";

const Home: React.FC = () => {
    const [showSteps, setShowSteps] = useState(false);

    useEffect(() => {
        console.log("Initializing AOS");
        AOS.init({
            duration: 1500,
            once: true, // Ensures animations happen only once
        });

        const timer = setTimeout(() => {
            console.log("Showing steps after 2 seconds");
            setShowSteps(true);
        }, 2000);

        return () => clearTimeout(timer);
    }, []);

    return (
        <div className="container">
            <section className="steps">
                <div className="step-row" data-aos="fade-up">
                    <div className="step" data-aos="fade-right">
                        <h2>Step 1: Data Modeling</h2>
                        <p>
                            Define the Data Model following the{" "}
                            <a
                                href="https://merode.econ.kuleuven.be/MERODE.html"
                                target="_blank"
                            >
                                MERODE Approach
                            </a>
                            .
                        </p>
                        <Link to="/domaindata">
                            <button className="get-started-button">
                                Get Started
                            </button>
                        </Link>
                    </div>
                    <div className="step" data-aos="fade-left">
                        <h2>Step 2: Process Modeling</h2>
                        <p>
                            Model, save and edit the Business Process using the
                            standard BPMN 2.0.
                        </p>
                        <Link to="/bpmn">
                            <button className="get-started-button">
                                Get Started
                            </button>
                        </Link>
                    </div>
                </div>

                <div
                    className="step"
                    style={{
                        width: "100%", // Full width
                        textAlign: "center", // Center-align text horizontally
                        marginBottom: "20px", // Optional space below the text
                    }}
                    data-aos="fade-up"
                >
                    <h2>Step 3: Process Monitoring, Analysis and Simulation</h2>
                    <p>
                        Track and analyze your process with this built-in tools.
                    </p>

                    <div
                        style={{
                            display: "flex", // Use flexbox to create two columns
                            justifyContent: "space-between", // Space out images evenly
                            gap: "20px", // Add space between images
                            width: "100%", // Ensure the container takes full width
                        }}
                        data-aos="fade-up"
                    >

                    <div
                                style={{
                                    width: "60%", // Adjust width for the text block
                                    height: "auto", // Let text take necessary height
                                    textAlign: "center", // Align text to the left
                                }}
                            >
                                <p>Nam sit amet arcu pellentesque, maximus lorem at, condimentum erat. Donec non sollicitudin leo, in suscipit neque. Fusce sapien tortor, tempor eu elementum id, lacinia non lectus. Cras at laoreet metus, eu tincidunt lorem. Vivamus nec dolor id felis volutpat gravida in eget leo. Proin faucibus scelerisque ante, at maximus ex sollicitudin ac. Sed dictum ornare tortor, id bibendum tortor efficitur id. Aliquam venenatis nisl at dui congue, non luctus turpis vulputate. Phasellus tincidunt ligula et mauris rhoncus blandit. Donec elementum lorem id quam blandit, pellentesque suscipit nunc malesuada. Curabitur convallis lacus id egestas porta. Quisque sit amet ornare enim. Quisque ac est sodales, elementum ex eu, gravida sapien. Sed mattis, nisl nec semper iaculis, justo odio fermentum mi, vel mattis massa elit quis erat.</p>
                            </div>

                    <img
                        style={{
                            width: "40%", // Adjust the width of the first image
                            height: "40%",
                            maxWidth: "100%", // Constrain image width
                        }}
                        src={monitoring}
                        alt="Monitoring Simulation"
                    />
                    
                </div>
                </div>               
            </section>
        </div>
    );
};

export default Home;