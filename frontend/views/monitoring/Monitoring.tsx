import React, { useEffect, useState, useRef } from "react";
import { BsDiagram2 } from "react-icons/bs";
import axios from "axios";
import { MdOutlineInsertChartOutlined, MdChecklist, MdCancel, MdCheckCircle, MdMonitorHeart, MdAutoGraph, MdPlayCircleOutline, MdExpandMore, MdExpandLess } from "react-icons/md";
import { Notyf } from "notyf";
import "./MonitCSS.css"
// @ts-ignore
import BpmnJS from "bpmn-js";
import "../microservice/fileList.css";

export default function Monitoring() {
  const [fileList, setFileList] = useState<string[]>([]);
  const [selectedFile, setSelectedFile] = useState<string | null>(null);
  const [isExpanded, setIsExpanded] = useState(false);
  const bpmnContainerRef = useRef<HTMLDivElement | null>(null);
  const [isDeployed, setIsDeployed] = useState(0);  // Traccia lo stato di deployment

  useEffect(() => {
    fetchDiagramList();
  }, []);

  useEffect(() => {
    if (selectedFile && isExpanded) {
      loadBpmnDiagram(selectedFile);
    }
  }, [selectedFile, isExpanded]);

  const notyf = new Notyf({
    duration: 3000,
    ripple: false,
    position: {
      x: "right",
      y: "top",
    },
    types: [
      {
        type: "warning",
        background: "orange",
        icon: {
          className: "material-icons",
          tagName: "i",
          text: "warning",
        },
      },
      {
        type: "error",
        background: "indianred",
        duration: 2000,
        dismissible: false,
      },
    ],
  });

  const fetchDiagramList = async () => {
    try {
      const response = await axios.get("/list-diagrams");
      setFileList(response.data);
    } catch (error) {
      console.error("Failed to fetch diagrams:", error);
      notyf.error("Failed to load diagram list!");
    }
  };

  const loadBpmnDiagram = async (file: string) => {
    try {
      const response = await axios.get(`/get-diagram/${file}`, {
        responseType: "text",
      });

      const bpmnJS = new BpmnJS({
        container: bpmnContainerRef.current,
      });

      await bpmnJS.importXML(response.data);
    } catch (error) {
      console.error("Failed to load BPMN diagram:", error);
      notyf.error("Failed to load BPMN diagram!");
    }
  };

  const toggleExpand = (file: string) => {
    if (selectedFile === file && isExpanded) {
      setIsExpanded(false);
      setSelectedFile(null);
    } else {
      setSelectedFile(file);
      setIsExpanded(true);
    }
  };

  const analyzeDiagram = () => {
    const monitoringUrl = `http://localhost:8081/operate`;
    window.open(monitoringUrl, "_blank");
  };

  const taskList = () => {
    const taskListUrl = `http://localhost:8082/tasklist`;
    window.open(taskListUrl, "_blank");
  };

  const optimizeDiagram = () => {
    const optimizeUrl = `http://localhost:8083/#/`;
    window.open(optimizeUrl, "_blank");
  };

  const deployDiagram = async () => {
    if (!selectedFile) return;
  
    try {
      const formData = new FormData();
      formData.append("file", selectedFile);
  
      // Effettua la chiamata al server per fare il deploy
      const response = await axios.post(
        "http://localhost:26500",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );
  
      // Verifica se il deploy è riuscito controllando la risposta
      if (response.status === 200 || response.status === 201) {
        setIsDeployed(1);  // Imposta il deploy come riuscito
        notyf.success(`Deployment of ${selectedFile} successful!`);
        console.log("Deploy Response:", response.data);
      } else {
        // Gestione del fallimento del deploy
        setIsDeployed(0);  // In caso di errore
        notyf.error(`Failed to deploy ${selectedFile}`);
      }
    } catch (error) {
      // Gestione di eventuali errori
      console.error("Deployment failed:", error);
      setIsDeployed(0);  // Imposta come non deployato
      notyf.error(`Failed to deploy ${selectedFile}`);
    }
  };

  return (
    <div style={{ margin: "15px" }}>
      <h2 style={{ marginLeft: "0px", marginTop: "20px" }}>BPMN Models</h2>

      {fileList.map((file, index) => (
        <div
          key={index}
          style={{
            border: isExpanded && selectedFile === file ? "none" : "1px solid rgba(0, 0, 0, 0.05)",
            margin: "10px",
            padding: "10px",
            borderRadius: "5px",
            fontSize: "15px",
            color: "black",
            background: isExpanded && selectedFile === file ? "#f0fcff" : "#ffffff",
          }}
        >
          <div style={{ display: "flex", alignItems: "center", cursor: "pointer" }} onClick={() => toggleExpand(file)}>
            <BsDiagram2 style={{ padding: "10px", fontSize: "22px" }} />
            <div
              className="file-info-item-name file-name"
              style={{
                fontSize: "14px",
                padding: "5px",
                color: "black",
                marginRight: "auto",
                whiteSpace: "nowrap",
                overflow: "hidden",
                textOverflow: "ellipsis",
                maxWidth: "80%",
              }}
              title={file}
            >
              {file}
              {isDeployed === 1 ? (
        <span className="badge deployed" style={{ marginLeft: "10px" }}>
          <MdCheckCircle style={{ marginRight: "5px" }} />
          Deployed
        </span>
      ) : (
        <span className="badge not-deployed" style={{ marginLeft: "10px" }}>
          <MdCancel style={{ marginRight: "5px" }} />
          Not Deployed
        </span>
      )}
            </div>
            {isExpanded && selectedFile === file ? (
              <MdExpandLess style={{ fontSize: "22px" }} />
            ) : (
              <MdExpandMore style={{ fontSize: "22px" }} />
            )}
          </div>
          {isExpanded && selectedFile === file && (
            <div style={{ display: "flex", flexDirection: "row", marginTop: "15px", backgroundColor: "#e6f7ff", padding: "10px", borderRadius: "8px" }}>
        {/* Container del diagramma BPMN */}
        <div style={{ border: "1px solid #ddd", padding: "10px", flex: "3", height: "auto", overflow: "auto", background: "#fff", borderRadius: "8px" }}>
            <h3 style={{ margin: "0", marginBottom: "10px" }}>BPMN Model</h3>
            <div ref={bpmnContainerRef} style={{ border: "1px solid #ddd", height: "650px", width: "2000px", overflowX: "auto", overflowY: "auto", backgroundColor: "#f9f9f9" }}></div>
        </div>

    {/* Pulsanti di controllo */}
    <div style={{ display: "flex", flexDirection: "column", justifyContent: "space-around", alignItems: "center", padding: "10px", flex: "1", gap: "10px", height: "100%" }}>
        {/* Pulsante Deploy */}
        <button style={{ width: "150px", height: "40px", background: "white", color: "#324e6c", fontSize: "15px", borderRadius: "5px", border: "1px solid #324e6c", cursor: "pointer", fontWeight: "bold", textAlign: "center", display: "flex", justifyContent: "center", alignItems: "center" }} onClick={deployDiagram}>
            <MdPlayCircleOutline style={{ marginRight: "5px" }} /> Deploy
        </button>

        {/* Pulsante Monitor */}
        <button style={{ width: "150px", height: "40px", background: "white", color: "#324e6c", fontSize: "15px", borderRadius: "5px", border: "1px solid #324e6c", cursor: "pointer", fontWeight: "bold", textAlign: "center", display: "flex", justifyContent: "center", alignItems: "center" }} onClick={analyzeDiagram}>
            <MdMonitorHeart style={{ marginRight: "5px" }} /> Monitor
        </button>

        {/* Pulsante Task List */}
        <button style={{ width: "150px", height: "40px", background: "white", color: "#324e6c", fontSize: "15px", borderRadius: "5px", border: "1px solid #324e6c", cursor: "pointer", fontWeight: "bold", textAlign: "center", display: "flex", justifyContent: "center", alignItems: "center" }} onClick={taskList}>
            <MdChecklist style={{ marginRight: "5px" }} /> Task List
        </button>

        {/* Pulsante Simulate */}
        <button disabled style={{width: "150px", height: "40px", background: "white", color: "#324e6c", fontSize: "15px", borderRadius: "5px", border: "1px solid #324e6c", cursor: "pointer", fontWeight: "bold", textAlign: "center", display: "flex", justifyContent: "center", alignItems: "center" }} onClick={optimizeDiagram}>
            <MdOutlineInsertChartOutlined style={{ marginRight: "5px" }} /> Simulate
        </button>

        {/* Pulsante Optimize */}
        <button disabled style={{width: "150px", height: "40px", background: "white", color: "#324e6c", fontSize: "15px", borderRadius: "5px", border: "1px solid #324e6c", cursor: "pointer", fontWeight: "bold", textAlign: "center", display: "flex", justifyContent: "center", alignItems: "center" }} onClick={optimizeDiagram}>
            <MdAutoGraph style={{ marginRight: "5px" }} /> Optimize
        </button>

        {/* Titolo e Sottotitolo */}
        <div style={{ textAlign: "center", marginTop: "20px" }}>
            <h3 style={{ margin: "0", color: "#324e6c" }}>Process Variables</h3>
            <p style={{ margin: "5px", fontSize: "14px", color: "#777" }}>Test 1</p>
            <p style={{ margin: "5px", fontSize: "14px", color: "#777" }}>Test 2</p>
        </div>
    </div>
  </div>
)}
        </div>
      ))}
    </div>
  );
}