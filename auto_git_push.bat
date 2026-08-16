@echo off
chcp 65001 >nul
cd /d "E:\MCR\GTY"

echo ========================================
echo   终极暴力强制推送脚本
echo   目标仓库: bii20081Aztec/CSSAHUV
echo ========================================
echo.

:: 配置用户信息
echo [1/5] 配置 Git 用户...
git config --global user.name "bii20081Aztec"
git config --global user.email "bii20081@example.com"
echo       完成
echo.

:: 初始化仓库
echo [2/5] 初始化本地仓库...
if exist ".git" (
    echo       已存在 .git，跳过初始化
) else (
    git init
    echo       初始化完成
)
echo.

:: 设置远程仓库
echo [3/5] 设置远程仓库地址...
git remote remove origin 2>nul
git remote add origin https://github.com/bii20081Aztec/CSSAHUV
echo       远程地址已设置
echo.

:: 创建空提交（暴力关键步骤）
echo [4/5] 创建空提交（保证有内容可推送）...
git commit --allow-empty -m "Force overwrite - %date% %time%"
echo       空提交已创建
echo.

:: 强制推送
echo [5/5] 暴力强制推送到远程仓库...
git push -u origin main --force

if errorlevel 1 (
    echo.
    echo [错误] 推送失败！
    echo 尝试使用 master 分支...
    git push -u origin master --force
)

echo.
echo ========================================
echo   完成！远程仓库已被覆盖
echo ========================================
echo.
echo 接下来你可以：
echo   1. 添加文件: git add .
echo   2. 提交文件: git commit -m "add files"
echo   3. 推送文件: git push
echo.
pause