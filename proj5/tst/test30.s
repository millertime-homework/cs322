	.align 4
A_A:
!locals=0, max_args=0
	save %sp,-96,%sp
! [RETURN (PARAM 1)]
	ret
	restore
	ret
	restore

	.global main
	.align 4
main:
!locals=1, max_args=2
	save %sp,-96,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
	mov 4,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 1)) (CONST 0)]
	mov 0,%l0
	st %l0,[%l1]
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [MOVE (TEMP 2) (CALL (NAME A_A) ( (VAR 1) (CONST 2)))]
	ld [%fp-4],%l0
	st %l0,[%sp+68]
	ld [2],%l2
	st %l2,[%sp+68]
	call A_A
	nop
	mov %o0,%l3
!>> Temp t2 assigned to reg %l4
	mov %o0,%l4
! [MOVE (FIELD (VAR 1) 0) (TEMP 2)]
	mov %l4,%l3
	ld [%fp-4],%l5
	st %l3,[%l5]
! [CALLST (NAME print) ( (FIELD (VAR 1) 0))]
	ld [%fp-4],%l3
	ld [%l3],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  8
!Total insts: 40
