from fastapi import FastAPI, File, UploadFile, Form
from pydantic import BaseModel
import cv2
import numpy as np
import json
import os

app = FastAPI()

DATA_FILE = "students_data.json"

def load_students_data():
    if os.path.exists(DATA_FILE):
        try:
            with open(DATA_FILE, "r") as file:
                data = json.load(file)
                for student_id, student_data in data.items():
                    student_data["faces"] = [
                        np.array(face, dtype=np.uint8) for face in student_data["faces"]
                    ]
                return data
        except json.JSONDecodeError:
            return {}
    return {}

def save_students_data(students_data):
    data_to_save = {}
    for student_id, data in students_data.items():
        data_to_save[student_id] = {
            "name": data["name"],
            "matricula": data["matricula"],
            "faces": [face.tolist() for face in data["faces"]]
        }
    with open(DATA_FILE, "w") as file:
        json.dump(data_to_save, file, indent=4)

def preprocess_image(image):
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    gray = cv2.equalizeHist(gray)
    resized_face = cv2.resize(gray, (100, 100))
    return resized_face

students_data = load_students_data()

class Student(BaseModel):
    name: str
    matricula: str

@app.post("/register-student/")
async def register_student(
    name: str = Form(...),
    matricula: str = Form(...),
    image: UploadFile = File(...)
):
    global students_data

    if matricula in students_data:
        return {"status": "error", "message": "Aluno já cadastrado"}

    image_bytes = await image.read()
    np_arr = np.frombuffer(image_bytes, np.uint8)
    img = cv2.imdecode(np_arr, cv2.IMREAD_COLOR)

    face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + "haarcascade_frontalface_default.xml")
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    faces = face_cascade.detectMultiScale(gray, scaleFactor=1.3, minNeighbors=5, minSize=(30, 30))

    if len(faces) == 0:
        return {"status": "error", "message": "Nenhum rosto detectado na imagem"}

    x, y, w, h = faces[0]
    face_image = img[y:y + h, x:x + w]
    preprocessed_face = preprocess_image(face_image)

    students_data[matricula] = {
        "name": name,
        "matricula": matricula,
        "faces": [preprocessed_face]
    }
    save_students_data(students_data)

    return {"status": "success", "message": "Aluno cadastrado com sucesso!"}

@app.get("/students/")
def list_students():
    students = [{"name": data["name"], "matricula": student_id} for student_id, data in students_data.items()]
    return {"status": "success", "students": students}