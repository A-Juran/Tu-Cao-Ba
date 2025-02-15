import { Avatar } from "@nextui-org/react";

/**
 * 聊天列表
 * @returns
 */
export default function chatGroupList() {
  return (
    <>
      <div className="chat-group-item group-active">
        <div className="avatar">
          <Avatar
            isBordered
            radius="full"
            src="https://i.pravatar.cc/150?u=a04258114e29026708c"
          />
        </div>
        <div className="group-detail">
            <div className="group-name">
                Tu-Cao-Ba
            </div>
            <div className="group-msg">
                最近一条信息……
            </div>
        </div>
      </div>
    </>
  );
}
