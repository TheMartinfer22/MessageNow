import React, { useState } from "react";
import axios from "axios";
import "./css/styles.css"

const EscreverMensagem = () => {

    const [mensagens, setMensagens] = useState("");

    function postMessage() {
        if (mensagens.length === 0) {
            return (
                alert("Você não pode deixar o campo vazio")
            )
        }
        axios.post("http://localhost:8080/", {
            message: mensagens
        })
    }

return (
    <form class="form-write-message">
        <textarea maxLength="200" placeholder="Escreva sua mensagem..." type="text" onChange={(event) => setMensagens(event.target.value)} />
        <button onClick={() => postMessage()}>Enviar</button>
    </form>
);
}
export default EscreverMensagem;