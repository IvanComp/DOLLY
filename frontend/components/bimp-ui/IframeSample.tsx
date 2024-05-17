import React from "react";

const BIMP_BASE_URL = "https://qbpsimulator.github.io/bimp-ui/";
const BIMP_CONTAINER_ID = "bimp-container";

const bimpConfig = {
    basicAuth: {
        username: "limited",
        password: "limited",
    },
};

interface IframeSampleState {
    uploadedData?: {
        fileName: string;
        fileContent: string | ArrayBuffer | null;
    };
}

export class IframeSample extends React.Component<{}, IframeSampleState> {
    constructor(props: {}) {
        super(props);
        this.state = {
            uploadedData: undefined,
        };
    }

    handleFileChosen = (file: File) => {
        const fileReader = new FileReader();
        fileReader.onloadend = () => {
            const content = fileReader.result;
            this.setState({
                uploadedData: {
                    fileName: "sample.bpmn",
                    fileContent: content ? btoa(content.toString()) : null,
                },
            }, this.loadBimpWithFile);
        };
        fileReader.readAsText(file);
    };

    render() {
        return (
            <div className="bimp-wrapper">
                <input
                    type="file"
                    id="file-iframe"
                    accept=".bpmn"
                    onChange={(e) => this.handleFileChosen(e.target.files![0])}
                />
                <iframe
                    id={BIMP_CONTAINER_ID}
                    src={this.getIframeUrl()}
                    title="iframe-title"
                    style={{
                        width: "100%",
                        height: 800,
                        overflowY: "auto",
                        margin: -7,
                        border: 0,
                    }}
                    onLoad={this.handleIframeLoaded}
                />
            </div>
        );
    }

    getIframeUrl() {
        const queryParams = [];
        const configStr = JSON.stringify(bimpConfig);
        queryParams.push(`bimpConfig=${encodeURIComponent(configStr)}`);
        queryParams.push(`post-init=true`);

        const queryStr = queryParams.join("&");
        return BIMP_BASE_URL + (queryStr ? "?" : "") + queryStr;
    }

    loadBimpWithFile = () => {
        const POST_MESSAGE_TO = BIMP_BASE_URL.substr(
            0,
            BIMP_BASE_URL.indexOf("/", 8)
        );
        const message = { ...this.state.uploadedData, type: "INIT" };
        const { contentWindow } = document.getElementById(BIMP_CONTAINER_ID) as HTMLIFrameElement;
        contentWindow?.postMessage(JSON.stringify(message), POST_MESSAGE_TO);
    }

    handleIframeLoaded = () => {
        // Implement your iframe load handling logic here, if any
    }
}
