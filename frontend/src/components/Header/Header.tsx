import React from "react";
import "./Header.scss";
import logo from "../../images/Kidedvisor.svg";
import back_afford from '../../images/back_aff.svg';
import close_afford from '../../images/close_aff.svg';
import Image from "next/image";

export default function Header() {
  return (
    <header className="header">
      <div className="affordance_container">
        <button>
          <Image
            className="back_afford"
            src={back_afford.src}
            width={14}
            height={14}
            alt="назад"
          />  
        </button>
        
        <button>
          <Image
            className="close_afford"
            src={close_afford.src}
            width={14}
            height={14}
            alt="закрыть"
          />  
        </button>
        
      </div>
      <div>
      <Image
        className="header__logo"
        src={logo.src}
        height={61}
        width={266}
        alt="логотип"
      /> 
      </div>       
    </header>
  );
}
