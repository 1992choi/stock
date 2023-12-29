import {
    ApertureIcon,
    CopyIcon,
    LayoutDashboardIcon, LoginIcon, MoodHappyIcon, TypographyIcon, UserPlusIcon
} from 'vue-tabler-icons';

export interface menu {
    header?: string;
    title?: string;
    icon?: any;
    to?: string;
    chip?: string;
    chipColor?: string;
    chipVariant?: string;
    chipIcon?: string;
    children?: menu[];
    disabled?: boolean;
    type?: string;
    subCaption?: string;
}

const sidebarItem: menu[] = [
    { header: 'Home' },
    {
        title: 'Dashboard',
        icon: LayoutDashboardIcon,
        to: '/main'
    },
    { header: 'utilities' },
    {
        title: 'Stock',
        icon: MoodHappyIcon,
        to: '/stock/list'
    },
    {
        title: 'Bookmark',
        icon: MoodHappyIcon,
        to: '/bookmark/list'
    },
];

export default sidebarItem;
