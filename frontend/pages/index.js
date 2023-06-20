import React, { useEffect } from "react";
import { useRouter } from "next/router";
import useSWR from "swr";
import clsx from "clsx";

import styles from "@/styles/pages.module.css";

export default function IndexPage() {
  const router = useRouter();

  const { data } = useSWR("/posts");

  const handleClick = ({ id }) => {
    router.push(`/${id}`);
  };

  return (
    <main className={styles.container}>
      <header className={styles.header}>
        <h2>Posts</h2>
        <small>Next.js SWR Infinite Loading Example</small>
      </header>

      {!data && (
        <div className={clsx(styles.loader, styles.loaderCenter)}>
          <span>Loading...</span>
        </div>
      )}

      {data?.map((item) => (
        <div
          key={item.id}
          className={styles.item}
          onClick={() => handleClick(item)}
        >
          <div className={styles.title}>{item.title}</div>
          <div className={styles.body}>{item.body}</div>
        </div>
      ))}
    </main>
  );
}
