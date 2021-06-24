import React, { useState, useEffect } from "react";
import Mensagem from "./Mensagem"
import axios from "axios";
import "./css/styles.css"

const ListarMensagens = () => {

    const [mensagens, setMensagens] = useState([])
    axios.get("http://localhost:8080/").then(response => setMensagens(response.data))

    useEffect(() => {}, []);


    return (
        <div class="message-box">

            {
                mensagens.map((mensagem, id) => {
                    return (
                        <Mensagem message={mensagem}key={id} />
                    )
                })
            }
            </div>

    );
}

export default ListarMensagens;