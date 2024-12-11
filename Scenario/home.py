import tkinter as tk
from tkinter import ttk
from tkinter import messagebox
import requests
import os
import threading
from flask import Flask, request, jsonify
import random
from PIL import Image, ImageTk

# Flask API Configuration
app = Flask(__name__)

# Initial IoT Device State
rooms = {
    f"Room {i}": {
        "smoke_sensor": False,
        "presence_sensor": False,  # Initially False
        "sprinkler": False,
        "num_people": 0  # No people initially
    } for i in range(1, 9)
}

@app.route('/sensor/status', methods=['GET'])
def get_status():
    return jsonify(rooms)

@app.route('/sensor/update', methods=['POST'])
def update_sensor():
    data = request.json
    room = data.get("room")
    device = data.get("device")
    value = data.get("value")
    
    if room in rooms and device in rooms[room]:
        rooms[room][device] = value
        return jsonify({"message": f"{device} in {room} updated to {value}"}), 200
    return jsonify({"error": "Invalid room or device"}), 400

@app.route('/sensor/activate_all_presence', methods=['POST'])
def activate_all_presence():
    # Cambiato: ora assegna o 0 o 1 persona
    for room in rooms:
        rooms[room]["presence_sensor"] = True
        rooms[room]["num_people"] = random.choice([0, 1])  # max 1 persona
    return jsonify({"message": "Presence sensors activated for all rooms"}), 200

@app.route('/sensor/<room>/smoke_sensor', methods=['GET'])
def get_smoke_sensor(room):
    """Ottieni il valore del smoke_sensor in una stanza specifica."""
    if room in rooms:
        value = rooms[room]["smoke_sensor"]
        return jsonify({f"smoke_sensor in {room}": value}), 200
    return jsonify({"error": "Invalid room"}), 400

@app.route('/sensor/<room>/presence_sensor', methods=['GET'])
def get_presence_sensor(room):
    """Ottieni il valore del presence_sensor in una stanza specifica."""
    if room in rooms:
        value = rooms[room]["presence_sensor"]
        return jsonify({f"presence_sensor in {room}": value}), 200
    return jsonify({"error": "Invalid room"}), 400

@app.route('/sensor/<room>/sprinkler', methods=['GET'])
def get_sprinkler(room):
    """Ottieni il valore dello sprinkler in una stanza specifica."""
    if room in rooms:
        value = rooms[room]["sprinkler"]
        return jsonify({f"sprinkler in {room}": value}), 200
    return jsonify({"error": "Invalid room"}), 400

@app.route('/sensor/<room>/sprinkler', methods=['POST'])
def update_sprinkler(room):
    """Aggiorna lo stato dello sprinkler in una specifica stanza."""
    if room in rooms:
        data = request.json
        value = data.get("value")

        if value is not None:
            rooms[room]["sprinkler"] = value
            return jsonify({"message": f"Sprinkler in {room} updated to {value}"}), 200
        return jsonify({"error": "Missing 'value' in request body"}), 400
    return jsonify({"error": "Invalid room"}), 400

@app.route('/sensor/sprinkler', methods=['POST'])
def update_all_sprinklers():
    """Aggiorna lo stato di tutti gli sprinkler."""
    data = request.json
    value = data.get("value")

    if value is not None:
        for room in rooms:
            rooms[room]["sprinkler"] = value
        return jsonify({"message": f"All sprinklers updated to {value}"}), 200
    return jsonify({"error": "Missing 'value' in request body"}), 400

@app.route('/start_process', methods=['POST'])
def start_process():
    # Simulate a random fire
    room = random.choice(list(rooms.keys()))
    for r in rooms:
        rooms[r]["smoke_sensor"] = False  # Reset all smoke sensors
    rooms[room]["smoke_sensor"] = True
    return jsonify({"message": f"Fire detected in {room}"}), 200

@app.route('/reset', methods=['POST'])
def reset_simulation():
    for room in rooms.values():
        room["smoke_sensor"] = False
        room["presence_sensor"] = False  # Reset to False
        room["sprinkler"] = False
        room["num_people"] = 0  # Reset to no people
    return jsonify({"message": "Simulation reset"}), 200
    
@app.route('/sensor/report', methods=['GET'])
def generate_report():
    """Genera un report dello stato di tutti i dispositivi in tutte le stanze."""
    report = {}
    for room, devices in rooms.items():
        smoke = devices["smoke_sensor"]
        presence = devices["presence_sensor"]
        num_people = devices["num_people"]

        # Determina la priorità in base alla logica
        if smoke and presence and num_people > 0:
            priority = "High"
        elif smoke or (presence and num_people > 0):
            priority = "Low"
        else:
            priority = "OK"

        report[room] = {
            "smoke_sensor": smoke,
            "presence_sensor": presence,
            "sprinkler": devices["sprinkler"],
            "num_people": num_people,
            "priority": priority
        }
    return jsonify({"report": report}), 200

class EvacuationGUI:
    def __init__(self, root):
        self.root = root
        self.root.title("Hospital Evacuation Simulation")
        self.report_generated = False 
        self.root.geometry("1300x900")  # Increased window size
        self.root.config(bg="white")  # White background

        base_dir = os.path.dirname(os.path.abspath(__file__))
        self.image_path = os.path.join(base_dir, "img", "Layout.png")  # Update with the correct path

        self.main_frame = tk.Frame(root, bg="white")
        self.main_frame.pack(fill=tk.BOTH, expand=True)

        self.image_frame = tk.Frame(self.main_frame, bg="white")
        self.image_frame.grid(row=0, column=0, sticky="n")

        self.load_and_display_image()

        self.right_frame = tk.Frame(self.main_frame, bg="white")
        self.right_frame.grid(row=0, column=1, sticky="nw", padx=10)

        self.cards_frame = tk.Frame(self.right_frame, bg="white")
        self.cards_frame.pack(side=tk.TOP, pady=10)
        self.rooms_status = {}

        for i in range(1, 9):
            room_frame = tk.Frame(self.cards_frame, borderwidth=2, relief="groove", padx=20, pady=10, bg="white")
            room_frame.grid_propagate(False)  
            room_frame.grid(row=(i-1)//2, column=(i-1)%2, padx=10, pady=10)

            room_label = tk.Label(room_frame, text=f"Treatment Room {i}", font=("Arial", 16, "bold"), bg="white", fg="black")
            room_label.pack()

            smoke_status = tk.Label(room_frame, text="🔥 Smoke: Loading...", font=("Arial", 14), bg="white", fg="black")
            smoke_status.pack()
            presence_status = tk.Label(room_frame, text="🧍 Presence: Loading...", font=("Arial", 14), bg="white", fg="black")
            presence_status.pack()
            sprinkler_status = tk.Label(room_frame, text="💧 Sprinkler: Loading...", font=("Arial", 14), bg="white", fg="black")
            sprinkler_status.pack()

            self.rooms_status[f"Room {i}"] = {
                "frame": room_frame,
                "label": room_label,
                "smoke": smoke_status,
                "presence": presence_status,
                "sprinkler": sprinkler_status
            }

        self.buttons_frame = tk.Frame(self.right_frame, bg="white")
        self.buttons_frame.pack(side=tk.TOP, pady=20)

        style = ttk.Style()
        style.theme_use("default")

        style.configure("Start.TButton", font=("Arial", 16))
        style.map("Start.TButton",
                  background=[("active", "green"), ("!active", "#4CAF50")],
                  foreground=[("active", "white"), ("!active", "white")])

        style.configure("Report.TButton", font=("Arial", 16))
        style.map("Report.TButton",
                  background=[("active", "blue"), ("!active", "#2196F3")],
                  foreground=[("active", "white"), ("!active", "white")])

        style.configure("Reset.TButton", font=("Arial", 16))
        style.map("Reset.TButton",
                  background=[("active", "red"), ("!active", "#FF5733")],
                  foreground=[("active", "white"), ("!active", "white")])

        self.start_button = ttk.Button(
            self.buttons_frame, text="Start Simulation", command=self.start_simulation, style="Start.TButton"
        )
        self.start_button.pack(side=tk.LEFT, padx=10)

        self.report_button = ttk.Button(
            self.buttons_frame, text="Generate Report", command=self.prepare_report, style="Report.TButton"
        )
        self.report_button.pack(side=tk.LEFT, padx=10)

        self.reset_button = ttk.Button(
            self.buttons_frame, text="Reset Simulation", command=self.terminate_simulation, style="Reset.TButton"
        )
        self.reset_button.pack(side=tk.LEFT, padx=10)

        self.report_frame = tk.Frame(self.image_frame, bg="white", borderwidth=2, relief="groove")
        self.report_frame.pack(side=tk.BOTTOM, fill=tk.BOTH, padx=20)

        self.update_status()
        self.schedule_update()

    def load_and_display_image(self):
        self.canvas = tk.Canvas(self.image_frame, bg="white", width=780, height=550)
        self.canvas.pack()

        image = Image.open(self.image_path)
        image = image.resize((780, 550))
        self.tk_image = ImageTk.PhotoImage(image)

        self.canvas.create_image(0, 0, anchor="nw", image=self.tk_image)

        self.room_positions = {
            "Room 1": (250, 440),
            "Room 2": (380, 495),
            "Room 3": (510, 510),
            "Room 4": (650, 495),           
            "Room 5": (280, 100),
            "Room 6": (350, 100),
            "Room 7": (600, 100),
            "Room 8": (655, 60),
        }

        self.room_indicators = {}
        for room, position in self.room_positions.items():
            x, y = position
            self.room_indicators[room] = self.canvas.create_oval(
                x - 10, y - 10, x + 10, y + 10, fill="white", outline="black"
            )

    def prepare_report(self):
        self.report_generated = True
        for widget in self.report_frame.winfo_children():
            widget.destroy()

        self.report_frame.config(borderwidth=0, relief="flat", bg="white")

        report_label = tk.Label(
            self.report_frame, 
            text="Treatment Rooms Status Report", 
            font=("Arial", 16, "bold"), 
            bg="white", 
            fg="black"
        )
        report_label.grid(row=0, column=0, columnspan=2, sticky="w", padx=10, pady=0)

        legend_label = tk.Label(
            self.report_frame, 
            text="Legend: 🔴 High Priority, 🟡 Low Priority, 🟢 OK", 
            font=("Arial", 12, "italic"), 
            bg="white", 
            fg="#7e7e7e"
        )
        legend_label.grid(row=1, column=0, columnspan=2, sticky="w", padx=10, pady=10)

        try:
            response = requests.get(f"http://127.0.0.1:5000/sensor/report")
            if response.status_code == 200:
                report_data = response.json().get("report", {})

                row_index = 2
                column_index = 0
                for room, devices in report_data.items():
                    smoke = devices["smoke_sensor"]
                    presence = devices["presence_sensor"]
                    num_people = devices["num_people"]

                    if smoke and presence and num_people > 0:
                        status = "[Presence Detected, Smoke Detected] - ‼️"
                        fg_color = "red"
                    elif smoke or (presence and num_people > 0):
                        status = "[Smoke or Presence Detected] - ⚠️"
                        fg_color = "gold"
                    else:
                        status = "[No Presence, No Smoke] - ✅"
                        fg_color = "green"

                    room_frame = tk.Frame(self.report_frame, bg="white")
                    room_frame.grid(row=row_index, column=column_index, sticky="w", padx=10, pady=5)

                    room_label = tk.Label(
                        room_frame, 
                        text=f"{room}:", 
                        font=("Arial", 14, "bold"), 
                        bg="white", 
                        fg="black",
                        anchor="w"
                    )
                    room_label.pack(side=tk.LEFT)

                    status_label = tk.Label(
                        room_frame, 
                        text=status, 
                        font=("Arial", 14), 
                        bg="white", 
                        fg=fg_color,
                        anchor="w"
                    )
                    status_label.pack(side=tk.LEFT)

                    column_index += 1
                    if column_index > 1:
                        column_index = 0
                        row_index += 1

                self.report_frame.update()
            else:
                messagebox.showerror("Error", "Failed to fetch report data.")
        except Exception as e:
            messagebox.showerror("Error", f"Error fetching report: {e}")

    def update_map(self):
        try:
            response = requests.get(f"http://127.0.0.1:5000/sensor/status")
            if response.status_code == 200:
                rooms_data = response.json()

                for room, data in rooms_data.items():
                    if room in self.room_indicators:
                        self.canvas.delete(self.room_indicators[room])

                        presence = data["presence_sensor"]
                        num_people = data["num_people"]

                        if presence and num_people > 0 and hasattr(self, "room_icons_cards"):
                            if room in self.room_icons_cards and len(self.room_icons_cards[room]) == num_people:
                                icons = " ".join(self.room_icons_cards[room])
                                x, y = self.room_positions[room]
                                self.room_indicators[room] = self.canvas.create_text(
                                    x, y, text=icons, font=("Arial", 20), fill="black"
                                )
                            else:
                                self.room_indicators[room] = None
                        else:
                            self.room_indicators[room] = None
            else:
                messagebox.showerror("Error", "Failed to fetch sensor data")
        except Exception as e:
            messagebox.showerror("Error", f"Error updating map: {e}")

        self.root.after(2000, self.update_map)
    
    def update_status(self):
        try:
            response = requests.get(f"http://127.0.0.1:5000/sensor/status")
            if response.status_code == 200:
                rooms_data = response.json()
                for room, devices in rooms_data.items():
                    self.update_sensor_display(room, devices)
                    self.update_card_background(room, devices, force_update=self.report_generated)
            else:
                messagebox.showerror("Error", "Failed to fetch sensor data")
        except Exception as e:
            messagebox.showerror("Error", f"Error fetching data: {e}")

    def update_card_background(self, room, devices, force_update=False):
        if not self.report_generated and not force_update:
            priority_color = "white"
        else:
            smoke = devices["smoke_sensor"]
            presence = devices["presence_sensor"]
            num_people = devices["num_people"]

            if smoke and presence and num_people > 0:
                priority_color = "#FFB3B3"
            elif smoke or (presence and num_people > 0):
                priority_color = "#FFFFB3"
            else:
                priority_color = "#B3FFB3"

        card_frame = self.rooms_status[room]["frame"]
        card_frame.config(bg=priority_color)

        self.rooms_status[room]["label"].config(bg=priority_color)
        self.rooms_status[room]["smoke"].config(bg=priority_color)
        self.rooms_status[room]["presence"].config(bg=priority_color)
        self.rooms_status[room]["sprinkler"].config(bg=priority_color)
        
    def update_sensor_display(self, room, devices):
        smoke = devices["smoke_sensor"]
        presence = devices["presence_sensor"]
        sprinkler = devices["sprinkler"]
        num_people = devices["num_people"]

        icons_set = ["👴🏼", "👴🏾", "👴🏿", "👵🏼", "👵🏾", "👵🏿"]

        if not hasattr(self, "room_icons_cards"):
            self.room_icons_cards = {r: [] for r in self.rooms_status}

        # Cambiato: verde se True, rosso se False
        self.rooms_status[room]["smoke"].config(
            text=f"🔥 Smoke: {'True' if smoke else 'False'}", fg="green" if smoke else "red"
        )

        if presence:
            if len(self.room_icons_cards[room]) != num_people:
                self.room_icons_cards[room] = [random.choice(icons_set) for _ in range(num_people)]
            icons = " ".join(self.room_icons_cards[room])
            presence_text = f"🧍 Presence: True ({icons})"
        else:
            presence_text = "🧍 Presence: False"
            self.room_icons_cards[room] = []

        self.rooms_status[room]["presence"].config(
            text=presence_text,
            fg="green" if presence else "red"
        )

        self.rooms_status[room]["sprinkler"].config(
            text=f"💧 Sprinkler: {'True' if sprinkler else 'False'}", fg="green" if sprinkler else "red"
        )

    def schedule_update(self):
        self.update_status()
        self.update_map()
        self.root.after(1000, self.schedule_update)

    def generate_report():
        report = {}
        icons_set = ["👴🏼", "👴🏾", "👴🏿", "👵🏼", "👵🏾", "👵🏿"]
        for room, devices in rooms.items():
            smoke = devices["smoke_sensor"]
            presence = devices["presence_sensor"]
            num_people = devices["num_people"]

            if smoke and presence and num_people > 0:
                icons = " ".join([random.choice(icons_set) for _ in range(num_people)])
                status = f"Smoke Detected and Presence Detected ({icons}) - Intervention Needed"
            elif smoke:
                status = "Smoke Detected - No Presence"
            elif presence and num_people > 0:
                icons = " ".join([random.choice(icons_set) for _ in range(num_people)])
                status = f"Presence Detected ({icons}) - No Smoke"
            else:
                status = "No Smoke or Presence"

            report[room] = status
        return report

    def start_simulation(self):
        try:
            response = requests.post(f"http://127.0.0.1:5000/start_process")
            if response.status_code == 200:
                flask_message = response.json()["message"]

                camunda_url = "http://localhost:8080/engine-rest/signal"
                camunda_payload = {
                    "name": "StartSimulationSignal",
                    "variables": {
                        "flaskMessage": {"value": flask_message, "type": "String"}
                    }
                }
                camunda_response = requests.post(camunda_url, json=camunda_payload)

                if camunda_response.status_code == 204:
                    messagebox.showinfo("Simulation Started",
                                        f"{flask_message}\nSignal sent to Camunda successfully.")
                else:
                    messagebox.showerror("Error", f"Failed to send signal to Camunda: {camunda_response.text}")

                self.update_status()
            else:
                messagebox.showerror("Error", "Failed to start the simulation")
        except Exception as e:
            messagebox.showerror("Error", f"Error starting simulation: {e}")

    def terminate_simulation(self):
        try:
            response = requests.post(f"http://127.0.0.1:5000/reset")
            if response.status_code == 200:
                messagebox.showinfo("Reset Successful", "All sensors have been reset.")
                self.report_generated = False

                for room in self.rooms_status.values():
                    room["frame"].config(bg="white")
                    room["label"].config(bg="white")
                    room["smoke"].config(bg="white")
                    room["presence"].config(bg="white")
                    room["sprinkler"].config(bg="white")

                for widget in self.report_frame.winfo_children():
                    widget.destroy()

                self.report_frame.config(borderwidth=0, relief="flat", bg="white")

            else:
                messagebox.showerror("Error", "Failed to reset sensors")
        except Exception as e:
            messagebox.showerror("Error", f"Error resetting sensors: {e}")

def start_flask():
    app.run(host="127.0.0.1", port=5000, debug=False, use_reloader=False)

if __name__ == "__main__":
    threading.Thread(target=start_flask, daemon=True).start()

    root = tk.Tk()
    app = EvacuationGUI(root)
    root.mainloop()