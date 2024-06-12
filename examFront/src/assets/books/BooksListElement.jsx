import { useState } from "react";
import "./BooksListElement.css";
import ModalBasic from "../elements/ModalBasic";
import BookDetailedInfo from "./BookDetailedInfo";

const BooksListElement = ({ data }) => {
  const [bookInfoModalVisibility, setBookInfoModalVisibility] = useState(false);

  const showModal = () => {
    setBookInfoModalVisibility(true);
  };

  const closeModal = () => {
    setBookInfoModalVisibility(false);
  };

  const { bookName } = data;

  return (
    <>
      <div onClick={showModal} className="booksListElement">
        <div>{bookName}</div>
      </div>
      <div>
        <ModalBasic
          isModalVisible={bookInfoModalVisibility}
          closeModal={closeModal}
          title={bookName}
        >
          <BookDetailedInfo data={data} />
        </ModalBasic>
      </div>
    </>
  );
};

export default BooksListElement;
