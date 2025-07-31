import { MdOutlineSearch } from "react-icons/md";
import Nav from "../components/nav/Nav";
import SecondaryGHButton from "../components/SecondaryGHButton";
import { NavLink } from "react-router-dom";

const ChatLayout = () => {
  return (
    <div className='flex'>
      {/* side navbar */}
      <aside className='min-h-dvh w-24 bg-gh-light py-10 grid justify-items-center overflow-clip'>
        <div className='logo'>
          <img src='./images/logo2.png' alt='Logo' />
        </div>
        <Nav />
      </aside>

      <div className='p-3 w-full min-h-dvh '>
        {/* content/chat/messages */}
        <div className='bg-gh-card rounded-2xl w-fit min-h-full flex flex-col p-4 overflow-clip'>
          {/* search and add section */}
          <div className='flex items-center gap-4'>
            <div className='bg-gh-primary-25 rounded-2xl max-w-[302px] flex items-center gap-2 ps-4 py-0.5'>
              <MdOutlineSearch className='w-7.5 h-7.5 fill-gh-dark-50' />
              <input
                className='w-full p-0.5 text-gh-dark-75 outline-none border-none placeholder:text-gh-dark-25'
                type='search'
                placeholder='Search Conversation'
              />
            </div>
            <SecondaryGHButton />
          </div>
          {/* filter link */}
          <nav className='filter__link mt-8 flex gap-5 items-center'>
            <NavLink to={`/`} className='py-2 px-5'>
              All
            </NavLink>
            <NavLink to={`/one`}>Online</NavLink>
          </nav>
        </div>
      </div>
    </div>
  );
};

export default ChatLayout;
