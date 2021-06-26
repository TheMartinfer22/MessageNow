import React from 'react'
import "./css/styles.css"

const Title = () => {
  return (
    <div>
      <p className="title not_selected"><strong>MessageNow</strong> - Instant Messages </p>
      <p className="description not_selected">
        Envie suas mensagens liveremente e com limite de <strong>200</strong> caracteres.
      </p>
    </div>
  )
}

export default Title;
