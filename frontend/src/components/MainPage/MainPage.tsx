import React from "react";
import SearchTag from "../SearchTag/SearchTag";
import "./MainPage.scss";
import Image from "next/image";
import filterImage from "../../images/filterIcon.svg";
import searchImage from "../../images/searchIcon.svg";

export default function MainPage() {
  return (
    <main className="main">
      <div className="tag-container">
        <SearchTag name="Спорт" />
        <SearchTag name="Наука" />
        <SearchTag name="Исскуство" />
      </div>
      <div className="search">
        <input
          className="search__input"
          placeholder="Направление, секция, знание"
        />
        <button className="search__button">
          <Image
            src={filterImage.src}
            width={25}
            height={23}
            alt="Иконка фильтра"
          />
        </button>

        <button className="search__button">
          <Image
            src={searchImage.src}
            width={25}
            height={25}
            alt="Иконка фильтра"
          />
        </button>
      </div>
    </main>
  );
}
