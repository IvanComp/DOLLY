import React, { useEffect } from 'react';
import * as THREE from 'three';

export default function ThreeDee() {
    useEffect(() => {
        const width = window.innerWidth;
        const height = window.innerHeight;

        // init
        const camera = new THREE.PerspectiveCamera(70, width / height, 0.01, 10);
        camera.position.z = 1;

        const scene = new THREE.Scene();

        const geometry = new THREE.BoxGeometry(0.2, 0.2, 0.2);
        const material = new THREE.MeshNormalMaterial();

        const mesh = new THREE.Mesh(geometry, material);
        scene.add(mesh);

        const renderer = new THREE.WebGLRenderer({ antialias: true });
        renderer.setSize(width, height);
        renderer.setAnimationLoop(animation);
        document.body.appendChild(renderer.domElement);

        // animation
        function animation(time: number) {
            mesh.rotation.x = time / 2000;
            mesh.rotation.y = time / 1000;

            renderer.render(scene, camera);
        }

        // Cleanup della scena e del renderer al momento della disconnessione
        return () => {
            // Verifica se dispose è disponibile sulla scena e nel renderer prima di chiamarlo
            if (scene && 'dispose' in scene) {
                // @ts-ignore
                scene.dispose();
            }
            if (renderer && 'dispose' in renderer) {
                renderer.dispose();
            }
        };
    }, []); // Dipendenza vuota per assicurarsi che l'effetto venga eseguito solo una volta

    return <div />;
}
