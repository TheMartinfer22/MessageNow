import React, { Component } from "react";
import "./css/styles.css"

class EscreverMensagem extends Component {

    constructor(props) {
        super(props);
        this.texto = "";
    }

    async _handlerMudancaTexto(evento) {
        this.texto = evento.target.value;
    }

    async _postMessage(evento) {
//        evento.preventDefault();
//        evento.stopPropagation();
        this.props.criarMensagem(this.texto);
    }

    render() {
        return (
            <form className="form-write-message"
            onChange={this._handlerMudancaTexto.bind(this)}
            onSubmit={this._postMessage.bind(this)}>
                <textarea className="not_selected" maxLength="200" placeholder="Escreva sua mensagem..." type="text"/>
                <button className="not_selected">Enviar</button>
            </form>
        );
    }
}
export default EscreverMensagem;
