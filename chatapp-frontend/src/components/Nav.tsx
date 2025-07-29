import {
  MdGroups,
  MdMessage,
  MdNotifications,
  MdSettings,
} from "react-icons/md";

const Nav = () => {
  return (
    <nav className='mt-auto'>
      <ul className='flex flex-col items-center gap-12'>
        <li className='bg-gh-primary flex justify-center items-center p-8'>
          <MdMessage className='w-9 h-9 fill-gh-light' />
        </li>
        <li>
          <MdNotifications />
        </li>
        <li>
          <MdGroups />
        </li>
        <li>
          <MdSettings />
        </li>
      </ul>
    </nav>
  );
};

export default Nav;
