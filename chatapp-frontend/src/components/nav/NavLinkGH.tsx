interface NavLinkProps {
  linkName: string;
  NavIcon: React.ElementType;
  activeLink: string;
  setActiveLink: (link: string) => void;
}

const NavLinkGH = ({
  linkName,
  NavIcon,
  activeLink,
  setActiveLink,
}: NavLinkProps) => {
  return (
    <li
      className={`${
        activeLink.toLowerCase() === linkName.toLowerCase() ? "active" : ""
      }  flex justify-center items-center p-8 transition cursor-pointer`}
      onClick={() => setActiveLink(linkName.toLowerCase())}
    >
      <NavIcon
        className={`w-9 h-9 ${
          activeLink === linkName ? "fill-gh-light" : "fill-gh-dark"
        } `}
      />
    </li>
  );
};

export default NavLinkGH;
