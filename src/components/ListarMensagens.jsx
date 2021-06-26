import React, { useState } from "react";
import Mensagem from "./Mensagem"
import axios from "axios";
import "./css/styles.css"
import { useEffect } from "react";

const ListarMensagens = () => {

    const [mensagens, setMensagens] = useState([])

   useEffect(() => {
       const timeoutID = window.setTimeout(() => {
           axios.get("http://40.124.42.151:8080/").then(response => setMensagens(response.data))
       }, 500);
       return () => window.clearTimeout(timeoutID);
   });

    // useEffect(() => {
    //     axios.get("http://40.124.42.151:8080/").then(response => setMensagens(response.data))
    // }, [])
    return (
        <div className="message-box">

            {
                mensagens.map((mensagem, id) => {
                    return (
                        <Mensagem message={mensagem} key={id} />
                    )
                })
            }
        </div>
    );
}

export default ListarMensagens;
