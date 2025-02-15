import { SuccessIcon, Menu } from "../../assets/icons/icons";

// 聊天记录

export default function ChatList(props) {
  const chatList = props.chatList;

  return (
    <div className="chat-list">
      <ChatItem list={chatList} />
    </div>
  );
}

function ChatItem({ list }) {
  const items = list.map((item) => (
    <div
      className={
        item.isMine
          ? "chat-item chat-list-position-right"
          : "chat-item chat-list-position-left"
      }
      key={item.id}
    >
      <div className={item.isMine ? "chat-item-is-mine" : "chat-item-not-mine"}>
        {/* 发送的内容 */}
        <div className="chat-content">{item.content}</div>
        {/* 发送的提示|时间 */}
        <div className="chat-tips">
          <div className="chat-time">{item.sendTime}</div>
          <div className="chat-status">
            <SuccessIcon fill={item.isMine ? "#fff" : "#000"} />
          </div>
        </div>
      </div>
    </div>
  ));

  return items;
}
