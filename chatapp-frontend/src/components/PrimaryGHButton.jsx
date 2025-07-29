const PrimaryGHButton = ({ buttonText }) => {
  return (
    <button className='bg-gh-primary border text-gh-light py-3 rounded-4xl transition hover:shadow-lg hover:border-gh-light'>
      {buttonText}
    </button>
  );
};

export default PrimaryGHButton;
