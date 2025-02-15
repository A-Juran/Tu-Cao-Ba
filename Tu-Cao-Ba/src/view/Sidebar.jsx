import "../assets/css/sidebar.scss";
import { Input } from "@nextui-org/input";
import { MailIcon, Menu } from "../assets/icons/icons";
import { Button } from "@nextui-org/react";
import ChatGroupList from './sidebar/component/chatGroupList'

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
              placeholder="搜索聊天"
              labelPlacement="outside"
              endContent={
                <MailIcon className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
              }
            />
          </div>
        </div>
        <div className="chat-group-box">
          <ChatGroupList/>
        </div>
      </div>
    </>
  );
}
