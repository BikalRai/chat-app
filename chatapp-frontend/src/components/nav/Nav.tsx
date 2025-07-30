import { useState } from "react";
import {
  MdGroups,
  MdMessage,
  MdNotifications,
  MdSettings,
} from "react-icons/md";
import NavLinkGH from "./NavLinkGH";

const Nav = () => {
  const [activeLink, setActiveLink] = useState<string>("message");
  console.log(activeLink);

  return (
    <nav className='mt-auto'>
      <ul className='flex flex-col items-center'>
        <NavLinkGH
          linkName='message'
          NavIcon={MdMessage}
          activeLink={activeLink}
          setActiveLink={setActiveLink}
        />
        <NavLinkGH
          linkName='notification'
          NavIcon={MdNotifications}
          activeLink={activeLink}
          setActiveLink={setActiveLink}
        />
        <NavLinkGH
          linkName='groups'
          NavIcon={MdGroups}
          activeLink={activeLink}
          setActiveLink={setActiveLink}
        />
        <NavLinkGH
          linkName='setting'
          NavIcon={MdSettings}
          activeLink={activeLink}
          setActiveLink={setActiveLink}
        />
      </ul>
    </nav>
  );
};

export default Nav;
