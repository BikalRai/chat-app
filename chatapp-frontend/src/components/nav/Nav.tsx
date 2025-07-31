import { useState } from "react";
import {
  MdGroups,
  MdMessage,
  MdNotifications,
  MdSettings,
} from "react-icons/md";
import NavLinkGH from "./NavLinkGH";

type NavLinkItems = {
  id: number;
  linkName: string;
  icon: React.ElementType;
};

const navLinkItems: NavLinkItems[] = [
  {
    id: 1,
    linkName: "message",
    icon: MdMessage,
  },
  {
    id: 2,
    linkName: "notification",
    icon: MdNotifications,
  },
  {
    id: 3,
    linkName: "groups",
    icon: MdGroups,
  },
  {
    id: 4,
    linkName: "setting",
    icon: MdSettings,
  },
];

const Nav = () => {
  const [activeLink, setActiveLink] = useState<string>("message");
  console.log(activeLink);

  return (
    <nav className='sideNav mt-auto'>
      <ul className='flex flex-col items-center'>
        {navLinkItems.map((item) => (
          <NavLinkGH
            key={item.id}
            linkName={item.linkName}
            NavIcon={item.icon}
            activeLink={activeLink}
            setActiveLink={setActiveLink}
          />
        ))}
        {/* <NavLinkGH
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
        /> */}
      </ul>
    </nav>
  );
};

export default Nav;
