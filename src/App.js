import ListarMensagens from "./components/ListarMensagens";
import EscreverMensagem from "./components/EscreverMensagem";
import Title from "./components/Title";
import { Component } from "react";
import axios from "axios";

class App extends Component {

  criarMensagem(mensagem) {
    if (mensagem.length === 0) {
      return (
        alert("Você não pode deixar o campo vazio")
      )
    } else {
      axios.post("http://localhost:8080/", {
        message: mensagem
      })
    }
  }

  render() {
    return (
        <section>
          <Title />
          <EscreverMensagem criarMensagem={this.criarMensagem} />
        <ListarMensagens/>
        </section>
    );
  }
}

export default App;
