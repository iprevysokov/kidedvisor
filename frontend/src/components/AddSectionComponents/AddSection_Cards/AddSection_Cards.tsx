import Image from "next/image";
import Button from "../../Button/Button";
import Delete from '../../../images/carddelete.svg';
import Plus from '../../../images/plusphoto_icon.svg';
import './AddSection_Cards.scss';
import { IAddSectionFormInput } from "@/src/app/(customHeader)/section_owner/add/page";
import { UseFormReturn } from "react-hook-form";

interface props {
    onNextClick: () => void;
    formMethods: UseFormReturn<IAddSectionFormInput>;
}

export default function AddSection_Cards({ onNextClick, formMethods: { getValues } }: props) {
    return (
        <div className="cards">
            <div className="cards_container">
                <div className="cards_title">
                    {getValues('sectionName')}
                </div>
                <div className="cards_items">
                    <div id="card">
                        <button className="delete_btn">
                            <Image className="delete_icons" alt="Удалить" src={Delete.src} width={28} height={28} />
                        </button>
                        <button className="cards_button">
                            <Image className='btn_icon' alt="Добавить" src={Plus.src} width={46} height={46} />
                            <span className="btn_icon_title">Добавить фото</span>
                        </button>
                    </div>
                </div>

                <Button title="Далее" additionalClass="button" onClick={onNextClick} />
            </div>
        </div>
    )
}