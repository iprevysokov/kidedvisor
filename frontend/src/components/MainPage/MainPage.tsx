import React from "react";
import SearchTag from "../SearchTag/SearchTag";
import "./MainPage.scss";
import Image from "next/image";
import filterImage from "../../images/filterIcon.svg";
import searchImage from "../../images/searchIcon.svg";
import mockBigCardImage from "../../images/test.jpg";
import mockBigCardLogo from "../../images/bigCardLogo.svg";
import SliderSection from "../SliderSection/SliderSection";

export default function MainPage() {
  return (
    <main className="main">
      <div className="tag-container">
        <SearchTag name="Спорт" />
        <SearchTag name="Наука" />
        <SearchTag name="Исскуство" />
      </div>
      <label className="search">
        <input
          className="search__input"
          placeholder="Название секции"
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
      </label>
      <section className="big-card">
        <p className="big-card__overlay-label">Наш выбор</p>
        <Image
          className="big-card__background-image"
          src={mockBigCardImage.src}
          alt="Задний фон секции"
          width={1920}
          height={1080}
        />
        <div className="big-card__container">
          <Image
            className="big-card__logo"
            src={mockBigCardLogo.src}
            alt="Логотип секции"
            width={133}
            height={37}
          />
          <h2 className="big-card__heading">Научись играть уже сегодня!</h2>
          <button className="big-card__button">Подробнее</button>
        </div>
      </section>
      <section className="sections">
        <SliderSection heading="Искусство" />
        <SliderSection heading="Спорт" />
        <SliderSection heading="Наука" />
      </section>
    </main>
  );
}
