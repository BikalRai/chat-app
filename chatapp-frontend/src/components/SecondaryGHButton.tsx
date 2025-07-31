import { MdOutlinePersonAddAlt } from "react-icons/md";

const SecondaryGHButton = () => {
  return (
    <button className='border-none outline-none rounded-2xl flex items-center justify-center gap-2 bg-gh-primary-25 font-medium px-7.5 py-2 transition hover:bg-gh-primary hover:text-gh-light'>
      <MdOutlinePersonAddAlt className='text-xl' />
      <span>Add</span>
    </button>
  );
};

export default SecondaryGHButton;
