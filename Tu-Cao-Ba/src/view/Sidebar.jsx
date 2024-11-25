import "../assets/css/sidebar.scss";
import { Input } from "@nextui-org/input";
import { MailIcon, Menu } from "../assets/icons/icons";
import { Button } from "@nextui-org/react";

export default function Sidebar() {
  return (
    <>
      <div className="sidebar">
        <div className="search">
          {/* 搜索按钮 */}
          <div className="tool">
            <Button isIconOnly variant="faded"  aria-label="Like">
              <Menu className="text-2xl text-default-400 pointer-events-none flex-shrink-0 color-[#707991]" />
            </Button>
          </div>
          {/* 搜索框 */}
          <div className="search-input">
            <Input
              type="email"
              placeholder="Search Chat"
              labelPlacement="outside"
              endContent={
                <MailIcon className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
              }
            />
          </div>
        </div>
      </div>
    </>
  );
}
