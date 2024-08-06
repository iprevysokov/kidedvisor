import React from "react";
import "./Header.scss";
import logo from "../../images/Kidedvisor.svg";
import Image from "next/image";
export default function Header() {
  return (
    <header className="header">
      <Image
        className="header__logo"
        src={logo.src}
        height={61}
        width={266}
        alt="логотип"
      />
    </header>
  );
}
