'use client'
import React from 'react';
import './SectionPage.scss';
import Header from '../Header/Header';
import Image from 'next/image';
import Popular from '@/src/images/popular_icon.svg';
import Filter from '@/src/images/filter_icon.svg';
import Avatar from '@/src/images/section_avatar.svg';
import Card_1 from '@/src/images/sectionpage_carousel_1.svg';
import Card_2 from '@/src/images/sectionpage_carousel_2.svg';
import Card_3 from '@/src/images/sectionpage_carousel_3.svg';
import Star from '@/src/images/rating_star.svg';
import Sneaker from '@/src/images/sneaker.svg';
import Arrow from '@/src/images/rightArrow.svg';
import ArrowOpen from '@/src/images/arrow_open.svg';
import ToggleSwitch from '../ToggleSwitch/ToggleSwitch';
import QuestionCircle from '@/src/images/question_circle.svg';

// Добавить карусель

export default function SectionPage() {
  return (
    <div className='section_page'>
      <Header />
      <div className='section_page_container'>
        <div className='section_page_filters'>

          <div id='popular_item'>
            <button id='popular_btn'>
              <Image 
                src={Popular.src}
                width={24}
                height={24}
                alt="Популярное"
              />
              <span>Популярное</span>   
            </button>
          </div>

          <div id='filter_item'>
            <button id='filter_btn'>
              <span>Фильтры</span>
              <Image
                src={Filter.src}
                width={24}
                height={24}
                alt='Фильтры'
              />  
            </button>
            
          </div>

        </div>  
        <div className='section_page_avatar'>
          <span id='section_rating'>4,9</span>
          <Image
          src={Avatar.src}
          width={328}
          height={208}
          alt='Фото секции'
          />
        </div>
        <div className='section_page_carousel'>
          <Image
          src={Card_1.src}
          width={104}
          height={72}
          alt='Фото секции'
          />
          <Image
          src={Card_2.src}
          width={104}
          height={72}
          alt='Фото секции'
          />
          <Image
          src={Card_3.src}
          width={104}
          height={72}
          alt='Фото секции'
          />
        </div>

        <div className='section_page_info'>
          <div className='section_page_info_container'>
            
            <div className='left_item'>
                <div className='section_page_info_rating_stars'>
                  <Image
                  src={Star.src}
                  width={14}
                  height={14}
                  alt='Звезда'
                  />
                  <Image
                  src={Star.src}
                  width={14}
                  height={14}
                  alt='Звезда'
                  />
                  <Image
                  src={Star.src}
                  width={14}
                  height={14}
                  alt='Звезда'
                  />
                  <Image
                  src={Star.src}
                  width={14}
                  height={14}
                  alt='Звезда'
                  />
                  <Image
                  src={Star.src}
                  width={14}
                  height={14}
                  alt='Звезда'
                  />
                </div>
                <span className='section_page_info_title'>Олимпийский</span>
                <br/>
                <span className='section_page_info_address'>Проспект мира д.1</span>
                <br/>
                <Image 
                  src={Sneaker.src}
                  width={20}
                  height={20}
                  alt='Вид спорта'
                />
                <span className='section_page_info_sport'>Спорт, футбол</span> 
            </div>
            
            <div className='right_item'>
              <button className='section_page_info_edit_button'>
                <span>Редактировать</span>
              </button>
            </div>

          </div>
        </div>
        <div className='section_page_poster'>
            <div className='section_page_poster_settings'>
              <div className='section_page_poster_settings_container'>
                <button className='section_page_poster_button'>
                  <span>Настроить афишу для секции</span>
                </button>
                <button className='section_page_poster_arrow'>
                  <Image 
                      src={ArrowOpen.src}
                      width={8}
                      height={13}
                      alt='Открыть'
                    />  
                </button>
              </div>
              <div className='open_settings_container'>
                <div className='open_settings_container_banner'>
                  <span className='open_settings_container_banner_title'>Разместить на главном баннере</span>
                  <ToggleSwitch />
                </div>
                <div className='open_settings_container_carousel'>
                  <span className='open_settings_container_carousel_title'>Разместить в карусели</span>
                  <ToggleSwitch />
                </div>
                <div className='open_settings_container_search'>
                  <span className='open_settings_container_search_title'>Баннер по результату поиска</span>
                  <ToggleSwitch />
                </div>
                <div className='open_settings_container_question'>
                  <span>Если возникли вопросы по рекламе, свяжитесь с нашим менеджером</span>
                </div>

                <div className='open_settings_container_send'>
                  <button>
                    <span>Отправить заявку</span>
                  </button>
                </div>
                <div className='open_settings_container_contact'>
                  <button>
                    <span>Связаться с менеджером</span>
                  </button>
                </div>
              
              </div>
            </div>
        </div>

        <div className='section_page_footer'>
          <div className='section_page_footer_container'>
            <div className='section_page_footer_delete_section'>
              <button>
                <span>Удалить секцию</span>
              </button>
            </div>
            <div className='section_page_footer_add_section'>
              <button>
                <span>Добавить секцию</span>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
