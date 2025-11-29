const bcrypt = require("bcryptjs")
const jwt = require("jsonwebtoken")
const db = require("../config/database")

//Registro de usuario
const register = async (req,res) => {
    try {
        const [ name, email, telefono, password ] = req.body;

        if(!name || !email || !telefono || !password){
            return res.status(400).json({
                success: false,
                message: "Todos los campos son obligatorios"
            });
        }

        const [usuarioexistente] = await db.query(
            "select * from where email = ?", [email]
        );

        if (usuarioexistente) {
            return res.status(400).json({
                success: false,
                message: "El correto electrónico ya está registrado"
            });
        }

        const salt = await bcrypt.genSalt(10); //Gensalt se usa para generar una sal aleatorio, un valor único que se añade a una contraseña antes de ser encriptada para hacer el hash más seguro
        const hashpassword = await bcrypt.hash(password,salt);

        const [result] = await db.query(
            "insert into usuarios"
        );

    } catch (error) {
        
    }
}