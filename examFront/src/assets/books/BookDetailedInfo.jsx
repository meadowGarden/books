const BookDetailedInfo = ({ data }) => {
  const { bookDescription, pageCount, isbn } = data;

  return (
    <>
      <div>{bookDescription}</div>
      <div>{pageCount}</div>
      <div>{isbn}</div>
    </>
  );
};

export default BookDetailedInfo;
