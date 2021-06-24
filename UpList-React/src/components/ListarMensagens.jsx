import React, { useState, useEffect } from "react";
import Mensagem from "./Mensagem"
import axios from "axios";
import "./css/styles.css"

const ListarMensagens = () => {

    const [mensagens, setMensagens] = useState([])


    useEffect(() => {
        axios.get("http://localhost:8080/").then(response => setMensagens(response.data))
    }, []);


    return (
        <div class="message-box">

            {
                mensagens.map((mensagem, index) => {
                    return (
                        <Mensagem message={mensagem}key={index} />
                    )
                })
            }
            </div>
    );
}

export default ListarMensagens;