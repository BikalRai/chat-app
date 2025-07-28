const GoogleGHButton = () => {
  return (
    <button className='flex justify-center gap-2 items-center border border-gh-dark-25 rounded-4xl py-1 transition hover:shadow-md'>
      <img
        className='w-8 h-w-8'
        src='./images/google-icon.svg'
        alt='Google Icon'
      />
      <span>Login with Google</span>
    </button>
  );
};

export default GoogleGHButton;
