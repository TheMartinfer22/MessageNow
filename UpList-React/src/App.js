import ListarMensagens from "./components/ListarMensagens";
import EscreverMensagem from "./components/EscreverMensagem";
import Title from "./components/Title";

const App = () => {
    return (
      <center>
        <section>
          <EscreverMensagem/>
          <ListarMensagens/>
        </section>
      </center>
    );
}

export default App;
