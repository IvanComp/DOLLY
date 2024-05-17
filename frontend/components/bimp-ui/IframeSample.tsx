import React from "react";

const BIMP_BASE_URL = "https://qbpsimulator.github.io/bimp-ui/";
const BIMP_CONTAINER_ID = "bimp-container";

const bimpConfig = {
    basicAuth: {
        username: "limited",
        password: "limited",
    },
};


interface IframeSampleProps { }

interface IframeSampleState {
    uploadedData?: {
        fileName: string;
        fileContent: string | ArrayBuffer | null;
    };
}

export class IframeSample extends React.Component<IframeSampleProps, IframeSampleState> {
    constructor(props: IframeSampleProps) {
        super(props);
        this.state = {
            uploadedData: undefined,
        };
    }

    handleFileChosen = (file: File) => {
        const fileReader = new FileReader();
        console.log(file);

        fileReader.onloadend = () => {
            const content = fileReader.result;
            this.setState({
                uploadedData: {
                    fileName: file.name,
                    fileContent: content ? btoa(content.toString()) : null,
                },
            }, this.loadBimpWithFile);
        };
        fileReader.readAsText(file);
    };

    loadBimpWithFile = (uploadedData = this.state.uploadedData) => {
        const POST_MESSAGE_TO = BIMP_BASE_URL.substr(
            0,
            BIMP_BASE_URL.indexOf("/", 8)
        );
        const message = { ...uploadedData, type: "INIT" };
        const { contentWindow } = document.getElementById(BIMP_CONTAINER_ID) as HTMLIFrameElement;
        contentWindow?.postMessage(JSON.stringify(message), POST_MESSAGE_TO);
    }

    handleIframeLoaded = () => {
        // Implement your iframe load handling logic here, if any
    }

    getIframeUrl() {
        const queryParams = [];
        const configStr = JSON.stringify(bimpConfig);
        queryParams.push(`bimpConfig=${encodeURIComponent(configStr)}`);
        queryParams.push(`post-init=true`);

        const queryStr = queryParams.join("&");
        return BIMP_BASE_URL + (queryStr ? "?" : "") + queryStr;
    }


    render() {
        return (
            <div className="bimp-wrapper" >
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
}
