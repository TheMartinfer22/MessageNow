import {React, useState} from 'react'
import "./css/styles.css"
import axios from "axios";

const EnviarMensagem = (props) => {

  const[count, setCount] = useState(props.message.reactions);

  function addReaction() {
    axios.get(`http://40.124.42.151:8080/reaction/${props.message.id}/add`)
    setCount(count +1)
  }

  async function removeReaction() {
    axios.get(`http://40.124.42.151:8080/reaction/${props.message.id}/remove`)
    setCount(count -1)
  }

  async function deleteMensagem(){
    await axios.delete(`http://40.124.42.151:8080/${props.message.id}/delete`);
    // window.location.reload();
  }

  return (
    <div className="content-mensagem">
      <div className="mensagem">
        <div class="mensagem-reactions">
          <svg onClick={() => addReaction()} xmlns="http://www.w3.org/2000/svg" className="up_down_delete" width="32" height="32" viewBox="0 0 24 24" stroke-width="1.5" stroke="#6f32be" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"></path><line x1="12" y1="5" x2="12" y2="19"></line><line x1="16" y1="9" x2="12" y2="5"></line><line x1="8" y1="9" x2="12" y2="5"></line></svg>
          <p className="not_selected">{count}</p>
          <svg onClick={() => removeReaction()} xmlns="http://www.w3.org/2000/svg" className="up_down_delete" width="32" height="32" viewBox="0 0 24 24" stroke-width="1.5" stroke="#6f32be" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"></path><line x1="12" y1="5" x2="12" y2="19"></line><line x1="16" y1="15" x2="12" y2="19"></line><line x1="8" y1="15" x2="12" y2="19"></line></svg>
        </div>
        <svg onClick={() => deleteMensagem()} xmlns="http://www.w3.org/2000/svg" className="up_down_delete trash" width="24" height="24" viewBox="0 0 24 24" stroke-width="1.5" stroke="#ff2825" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none" /><line x1="4" y1="7" x2="20" y2="7" /><line x1="10" y1="11" x2="10" y2="17" /><line x1="14" y1="11" x2="14" y2="17" /><path d="M5 7l1 12a2 2 0 0 0 2 2h8a2 2 0 0 0 2 -2l1 -12" /><path d="M9 7v-3a1 1 0 0 1 1 -1h4a1 1 0 0 1 1 1v3" /></svg>
        <div className="mensagem-text">
          <p className="not_selected mensagem-m">{props.message.message}</p>
        </div>
      </div>
    </div>
  )
}

export default EnviarMensagem;
