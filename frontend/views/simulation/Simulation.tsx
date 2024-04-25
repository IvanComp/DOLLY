import React, { useEffect } from 'react';
//import * as Bimp from 'bimp-ui';
//import 'bimp-ui/bimp-ui.sass';

export default function Simulation() {
    const bimpConfig = {
        protocol: "http://",
        host: "www.qbp-simulator.com:8080",
        url: "/qbp-simulator/rest/Simulation",
        basicAuth: {
            username: "limited",
            password: "limited"
        },
        linkPrefix: "",
        errorStackApiKey: ""
    };

    const initialFiles = [
        {
            name: 'file1.bpmn',
            contents: '<?xml version="1.0" encoding="UTF-8"?>\n' +
                '<bpmn:definitions xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:bpmn="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:dc="http://www.omg.org/spec/DD/20100524/DC" xmlns:di="http://www.omg.org/spec/DD/20100524/DI" id="Definitions_0r03v5b" targetNamespace="http://bpmn.io/schema/bpmn" exporter="bpmn-js (https://demo.bpmn.io)" exporterVersion="17.3.0">\n' +
                '  <bpmn:process id="Process_0cyh8r8" isExecutable="false">\n' +
                '    <bpmn:startEvent id="StartEvent_1rsqjat">\n' +
                '      <bpmn:outgoing>Flow_03bfuei</bpmn:outgoing>\n' +
                '    </bpmn:startEvent>\n' +
                '    <bpmn:task id="Activity_0x5v08n">\n' +
                '      <bpmn:incoming>Flow_03bfuei</bpmn:incoming>\n' +
                '      <bpmn:outgoing>Flow_0t32neu</bpmn:outgoing>\n' +
                '    </bpmn:task>\n' +
                '    <bpmn:sequenceFlow id="Flow_03bfuei" sourceRef="StartEvent_1rsqjat" targetRef="Activity_0x5v08n" />\n' +
                '    <bpmn:endEvent id="Event_1o85t1b">\n' +
                '      <bpmn:incoming>Flow_0t32neu</bpmn:incoming>\n' +
                '    </bpmn:endEvent>\n' +
                '    <bpmn:sequenceFlow id="Flow_0t32neu" sourceRef="Activity_0x5v08n" targetRef="Event_1o85t1b" />\n' +
                '  </bpmn:process>\n' +
                '  <bpmndi:BPMNDiagram id="BPMNDiagram_1">\n' +
                '    <bpmndi:BPMNPlane id="BPMNPlane_1" bpmnElement="Process_0cyh8r8">\n' +
                '      <bpmndi:BPMNShape id="_BPMNShape_StartEvent_2" bpmnElement="StartEvent_1rsqjat">\n' +
                '        <dc:Bounds x="152" y="102" width="36" height="36" />\n' +
                '      </bpmndi:BPMNShape>\n' +
                '      <bpmndi:BPMNShape id="Activity_0x5v08n_di" bpmnElement="Activity_0x5v08n">\n' +
                '        <dc:Bounds x="240" y="80" width="100" height="80" />\n' +
                '      </bpmndi:BPMNShape>\n' +
                '      <bpmndi:BPMNShape id="Event_1o85t1b_di" bpmnElement="Event_1o85t1b">\n' +
                '        <dc:Bounds x="392" y="102" width="36" height="36" />\n' +
                '      </bpmndi:BPMNShape>\n' +
                '      <bpmndi:BPMNEdge id="Flow_03bfuei_di" bpmnElement="Flow_03bfuei">\n' +
                '        <di:waypoint x="188" y="120" />\n' +
                '        <di:waypoint x="240" y="120" />\n' +
                '      </bpmndi:BPMNEdge>\n' +
                '      <bpmndi:BPMNEdge id="Flow_0t32neu_di" bpmnElement="Flow_0t32neu">\n' +
                '        <di:waypoint x="340" y="120" />\n' +
                '        <di:waypoint x="392" y="120" />\n' +
                '      </bpmndi:BPMNEdge>\n' +
                '    </bpmndi:BPMNPlane>\n' +
                '  </bpmndi:BPMNDiagram>\n' +
                '</bpmn:definitions>\n'
        },
    ];

    useEffect(() => {
        // Assicurati che il contenitore sia già nel DOM
        document.addEventListener('DOMContentLoaded', () => {
            // Inizializza Bimp con il contenitore, configurazione e file iniziali
           // Bimp.init('root-container', bimpConfig, initialFiles);
        });

        // Pulizia dell'evento
        return () => {
            document.removeEventListener('DOMContentLoaded', () => {
              //  Bimp.init('root-container', bimpConfig, initialFiles);
            });
        };
    }, []); // L'array vuoto assicura che l'effetto venga eseguito solo una volta dopo il primo rendering

    return (
        <div className="flex flex-col h-full items-center justify-center p-l text-center box-border">
            <div id='root-container'></div>
        </div>
    );
}
